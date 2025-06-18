#!/bin/bash

# Nome da rede
NETWORK_NAME="kong-net"

# Verifica se Docker está instalado
if ! command -v docker &> /dev/null
then
    echo "❌ Docker não está instalado. Instale antes de continuar."
    exit 1
fi

# Cria rede se não existir
if ! docker network ls --format '{{.Name}}' | grep -q "$NETWORK_NAME"; then
    docker network create $NETWORK_NAME
fi

# Cria volumes se necessário
VOLUMES=("kong_data" "konga_data")
for volume in "${VOLUMES[@]}"
do
    if ! docker volume ls --format '{{.Name}}' | grep -q "$volume"; then
        docker volume create "$volume"
    fi
done

# Inicia banco de dados PostgreSQL
docker run -d --name kong-database \
  --network $NETWORK_NAME \
  -p 5432:5432 \
  -e POSTGRES_USER=kong \
  -e POSTGRES_DB=kong \
  -e POSTGRES_PASSWORD=kong \
  postgres:13

# Aguarda banco iniciar
echo "⏳ Aguardando banco de dados iniciar..."
sleep 10

# Executa migração do Kong
docker run --rm \
  --network $NETWORK_NAME \
  -e KONG_DATABASE=postgres \
  -e KONG_PG_HOST=kong-database \
  -e KONG_PG_PASSWORD=kong \
  kong:3.7 kong migrations bootstrap

# Inicia o Kong Gateway
docker run -d --name kong \
  --network $NETWORK_NAME \
  -e KONG_DATABASE=postgres \
  -e KONG_PG_HOST=kong-database \
  -e KONG_PG_PASSWORD=kong \
  -e KONG_PROXY_ACCESS_LOG=/dev/stdout \
  -e KONG_ADMIN_ACCESS_LOG=/dev/stdout \
  -e KONG_PROXY_ERROR_LOG=/dev/stderr \
  -e KONG_ADMIN_ERROR_LOG=/dev/stderr \
  -e KONG_ADMIN_LISTEN=0.0.0.0:8001, 0.0.0.0:8002 \
  -p 8000:8000 \
  -p 8443:8443 \
  -p 8001:8001 \
  -p 8002:8002 \
  kong:3.7

# Inicia o Konga
docker run -d --name konga \
  --network $NETWORK_NAME \
  -p 1337:1337 \
  -e DB_ADAPTER=postgres \
  -e DB_HOST=kong-database \
  -e DB_PORT=5432 \
  -e DB_USER=kong \
  -e DB_PASSWORD=kong \
  -e DB_DATABASE=kong \
  -e NODE_ENV=production \
  pantsel/konga

# Mensagens finais
echo ""
echo "✅ Kong Gateway e Konga iniciados com sucesso!"
echo "🔗 Acesse:"
echo " - Admin API: http://localhost:8001"
echo " - Admin GUI (Kong): http://localhost:8002"
echo " - Konga: http://localhost:1337"
