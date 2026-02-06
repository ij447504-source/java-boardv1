# boardv1 프로젝트 분석

## 개요
`boardv1` 프로젝트는 Spring Boot 웹 애플리케이션으로, 게시판 기능을 제공합니다.

## 기술 스택
*   **프레임워크:** Spring Boot, Spring Data JPA, Spring MVC
*   **데이터베이스:** H2 (인메모리)
*   **템플릿 엔진:** Mustache

## 아키텍처
전형적인 3계층 아키텍처를 따릅니다:
*   **Controller:** HTTP 요청을 처리하고 응답을 반환합니다.
*   **Service:** 비즈니스 로직을 처리합니다.
*   **Repository:** 데이터베이스와의 상호작용을 담당합니다.

## 주요 엔티티 및 관계
프로젝트는 `User`, `Board`, `Reply` 세 가지 주요 엔티티를 중심으로 구성되어 있습니다.

*   **User (사용자)**
    *   사용자 정보를 담는 JPA 엔티티입니다 (`user_tb` 테이블).
    *   필드: 사용자 이름, 비밀번호, 이메일 등.

*   **Board (게시글)**
    *   게시글 정보를 담는 JPA 엔티티입니다 (`board_tb` 테이블).
    *   **User와의 관계:** `@ManyToOne` 관계로, 하나의 `User`가 여러 개의 `Board`를 작성할 수 있습니다.
    *   **Reply와의 관계:** `@OneToMany` 관계로, 하나의 `Board`에 여러 개의 `Reply`가 있을 수 있습니다.
    *   **특이사항:** 관계 설정 시 `FetchType.EAGER`가 사용되었습니다. 이는 N+1 문제와 같은 성능 저하를 일으킬 수 있으므로 주의 깊은 검토가 필요합니다.

*   **Reply (댓글)**
    *   게시글에 대한 댓글 정보를 담는 JPA 엔티티로 예상됩니다. (상세 구조는 추가 분석 필요)

## 디렉토리 구조 (주요 부분)
*   `src/main/java/com/example/boardv1/`: 핵심 Java 소스 코드.
    *   `board/`: 게시글 관련 Controller, Service, Repository, Entity, DTO.
    *   `reply/`: 댓글 관련 Controller, Service, Repository, Entity, DTO.
    *   `user/`: 사용자 관련 Controller, Service, Repository, Entity, DTO.
*   `src/main/resources/templates/`: Mustache 템플릿 파일.
    *   `header.mustache`: 공통 헤더.
    *   `board/`: 게시글 관련 템플릿 (예: `save-form.mustache`, `update-form.mustache`).
    *   `user/`: 사용자 관련 템플릿 (예: `join-form.mustache`, `login-form.mustache`).
*   `src/main/resources/static/`: 정적 리소스 (CSS, JavaScript, 이미지 등).
    *   `summernote/`: Summernote 에디터 파일들이 이미 포함되어 있음.
*   `build.gradle`: 프로젝트 의존성 및 빌드 설정 파일.

## 잠재적 개선점 및 추가 분석 필요 영역
*   **`FetchType.EAGER` 사용**: `Board` 엔티티의 관계에서 `FetchType.EAGER` 사용은 성능 문제를 야기할 수 있습니다. 지연 로딩(`FetchType.LAZY`)으로 변경하고 필요 시 페치 조인 등을 사용하는 것을 고려해 볼 수 있습니다.
*   **`Reply` 모듈 상세 분석**: `Reply` 엔티티의 구체적인 필드와 Controller, Service, Repository 구현에 대한 추가 분석이 필요합니다.
*   **UI 템플릿 분석**: Mustache 템플릿 파일들을 더 자세히 분석하여 애플리케이션의 사용자 인터페이스 및 흐름을 이해할 필요가 있습니다.