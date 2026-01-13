# SchoolApp

Application Spring Boot (Maven) avec Thymeleaf, Spring Security, Spring Data JPA et Validation.

## Fonctionnalités
- Authentification via `/login` (Bootstrap).
- Rôles : `ADMIN`, `TEACHER`, `STUDENT`.
- Règles d'accès :
  - `ADMIN` : accès total.
  - `TEACHER` : accès aux pages `/teacher/**`.
  - `STUDENT` : accès aux pages `/student/**`.
- Base H2 par défaut (console activée).
- Profil MySQL prêt à l'emploi.
- Admin par défaut au démarrage : `admin@demo.com / admin123`.

## Structure des dossiers
```
src/main/java/com/example/schoolapp
├── SchoolAppApplication.java
├── config
│   ├── DataInitializer.java
│   └── SecurityConfig.java
├── controller
│   ├── HomeController.java
│   ├── StudentController.java
│   └── TeacherController.java
├── entity
│   ├── Role.java
│   ├── StudentProfile.java
│   ├── TeacherProfile.java
│   └── User.java
├── repository
│   ├── RoleRepository.java
│   ├── StudentProfileRepository.java
│   ├── TeacherProfileRepository.java
│   └── UserRepository.java
└── service
    └── CustomUserDetailsService.java

src/main/resources
├── application.yml
├── application-mysql.yml
└── templates
    ├── home.html
    ├── login.html
    ├── student
    │   └── index.html
    └── teacher
        └── index.html
```

## Lancer l'application (H2)
```bash
mvn spring-boot:run
```

- App : http://localhost:8080
- H2 Console : http://localhost:8080/h2-console
  - JDBC URL : `jdbc:h2:mem:schoolapp`
  - User : `sa`
  - Password : (vide)

## Basculer vers MySQL
1. Créez une base `schoolapp` dans MySQL.
2. Mettez à jour `src/main/resources/application-mysql.yml` avec vos identifiants.
3. Lancez avec le profil MySQL :
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```

## Admin par défaut
- Email : `admin@demo.com`
- Mot de passe : `admin123`

## Pages utiles
- `/login`
- `/teacher`
- `/student`
