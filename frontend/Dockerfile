# 1. Aşama: Uygulamayı Derleme (Build)
FROM node:20-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# 2. Aşama: Derlenen Uygulamayı Sunma (Nginx)
FROM nginx:stable-alpine
COPY --from=build /app/dist /usr/share/nginx/html
# Nginx için varsayılan konfigürasyonu gerekirse buraya ekleyebilirsin
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
