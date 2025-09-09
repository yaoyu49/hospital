# Hospital Outpatient System

前后端分离的医院门诊管理系统（原型/教学用），支持患者建档、挂号分诊、门诊接诊、处方/医技、发药、结算与报表。

## 技术栈
- Frontend: Vue3 + TS + Vite + Pinia + Vue Router + Element Plus
- Backend: Spring Boot 3 (Java 17) + Spring Security + JPA + MySQL + Redis + RabbitMQ + Flyway + OpenAPI

## 快速开始
1) 启动基础设施
```bash
docker compose -f docker/docker-compose.yml up -d
```
2) 启动后端
```bash
cd backend
./mvnw spring-boot:run
```
3) 启动前端
```bash
cd frontend
pnpm i
pnpm dev
```

默认账号：admin / admin123（仅开发环境）

## 目录
- backend: 后端服务
- frontend: 前端 Web
- docs: 架构设计与 OpenAPI
- docker: 基础设施（DB/Redis/RabbitMQ）