# Usa a imagem base do Node.js na versão 20
FROM node:20 as builder

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos
COPY . .

# Instala as dependências
RUN npm install

# Constrói a aplicação
RUN npm run build

# Usa a imagem base do Nginx
FROM nginx:alpine

# Copia os arquivos construídos para o diretório do Nginx
COPY --from=builder /app/dist/frontend/browser /usr/share/nginx/html

# Copia o arquivo de configuração do Nginx
COPY nginx.conf /etc/nginx/nginx.conf
COPY mime.types /etc/nginx/mime.types

# Expõe a porta configurada no Nginx
EXPOSE 90

# Comando para iniciar o Nginx
CMD ["nginx", "-g", "daemon off;"]
