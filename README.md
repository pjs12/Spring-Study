# Spring-Study

---
### Spring Boot Project 구성
- `build.gradle` dependencies 추가
- DB(Oracle DB) 연동

---
### Oracle Database 구성
- **Oracle DB** 21c XE (Express Edition) 설치
  - 무료 이용 가능
  - 가볍고 간단한 테스트/개발용

- **테이블 스페이스** 정의
  - 데이터베이스 객체 (테이블, 인덱스 등)가 물리적으로 저장되는 논리적 저장소
  - ```sql
    -- 테이블 스페이스 생성 및 정의
    CREATE TABLESPACE '테이블 스페이스 이름'
    DATAFILE '경로/파일이름.dbf'
    SIZE '데이터 파일 초기 크기'M
    AUTOEXTEND ON
    NEXT '추가 크기'M MAXSIZE '데이터 파일 확장 최대 크기'M;
    
    -- 테이블 스페이스 조회
    SELECT tablespace_name, file_name
    FROM dba_data_files
    WHERE tablespace_name = '테이블 스페이스 이름';
  
- **계정 생성** 및 테이블 스페이스 할당
  - ```sql
    CREATE USER '계정 이름' IDENTIFIED BY '비밀번호'
    DEFAULT TABLESPACE '테이블 스페이스 이름'
    QUOTA '할당 용량'M ON '테이블 스페이스 이름';
  - 생성한 계정 이름은 대문자로 생성됨 주의
    
- 계정 **권한 부여**
  - ```sql
    -- 데이터베이스 내에서 객체 생성, 접근 권한 부여
    GRANT CONNECT, RESOURCE TO '계정 이름';
    
    -- 테이블 스페이스 사용 권한 부여
    ALTER USER '계정 이름' QUOTA UNLIMITED ON '테이블 스페이스 이름';
    
- 생성된 계정으로 접속
  - ```sql
    sqlplus '계정 이름'/'비밀번호'@XEPDB1

- 오류 발생 시
  - ORA-65048: 플러그인할 수 있는 데이터베이스 XEPDB1에서 현재 DDL 문을 처리하는 중
      오류가 발생했습니다.
    - Oracle 12c 이상부터 **Multitenant 구조(CBD + PDB)로 구성**
    - 계정은 PDB 내부에 생성되어야 하는데 CDB에 접속된 상태여서 발생하는 오류
    - PDB로 접속 전환
    - ```sql
      ALTER SESSION SET CONTAINER = XEPDB1;

---
### Spring Batch
- 배치 작업이 필요한 Job 생성
- API 호출을 통한 일회성 Job 실행
- Quartz Scheduler를 이용한 스케줄링 관리
