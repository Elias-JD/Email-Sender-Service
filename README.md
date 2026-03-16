# Email-Sender-Service
Es un microservicio backend desarrollado con Spring Boot 4 y Java 21 que actúa como un motor centralizado de notificaciones, permitiendo el envío de comunicaciones en diversos formatos (texto, archivos y HTML) de manera eficiente y escalable.

## Funcionalidades Principales
Standard Messaging: Envío de correos electrónicos en texto plano optimizado para notificaciones rápidas y reportes de bajo consumo de recursos.

Binary Data Support (Multi-part): Capacidad para gestionar y enviar archivos adjuntos (documentos, imágenes, PDF) directamente a través de peticiones HTTP, procesándolos de manera eficiente.

Dynamic HTML Templates (Thymeleaf): Implementación de un motor de plantillas para correos profesionales. Permite el envío de mensajes visualmente atractivos con datos dinámicos e imágenes incrustadas.

Validación y Manejo de Errores Centralizado: Implementación de Jakarta Bean Validation para asegurar la integridad de los datos de entrada, junto con un GlobalExceptionHandler que intercepta excepciones y fallos, devolviendo respuestas JSON descriptivas.

Contenerización con Docker: Configuración completa mediante Dockerfile y Docker Compose para un despliegue rápido y consistente mediante variables de entorno.

## Tecnologías Implementadas
- Lenguaje: Java 21 (LTS)

- Framework: Spring Boot 4

- Motor de Plantillas: Thymeleaf

- Infraestructura de Correo: Spring Mail (JavaMailSender)

- Validación: Jakarta Bean Validation

- Contenerización: Docker & Docker Compose
