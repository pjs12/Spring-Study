# Quartz Scheduler를 이용한 Spring Batch Job 관리

---
### BatchConfig
- Spring Batch의 기본 인프라 빈을 정의하고 Batch Job들을 정상적으로 실행 가능하게 만드는 환경을 구성
#### JobRegistry
- 등록된 Job을 이름 기반으로 찾기 위한 저장소
#### JobRegistrySmartInitializingSingleton
- Spring Context 초기화 과정에서 모든 Job 빈을 찾아 JobRegistry에 자동 등록
- Spring Batch 5.2 버전이상에서 JobRegistryBeanPostProcessor이 Deprecated 되면서 대체 사용

---
## Quartz 흐름도
![Quartz흐름도.jpg](Quartz%ED%9D%90%EB%A6%84%EB%8F%84.jpg)

### AutowiringSpringBeanJobFactory
- Spring Job의 의존성 주입을 Quartz Job에 적용하기 위한 커스텀 JobFactory
  - 개발자는 Spring Job을 구성하고 Bean으로 생성
  - Quartz Scheduler를 이용하기 위해서는 Quartz Job Instance가 필요
  - ${\textsf{\color{red}자동으로 Spring Job Bean을 이용해 Quartz Job Bean 생성하기 위한 JobFactory}}$

### QuartzConfig
- Quartz 스케줄러의 설정을 잡아주는 config 클래스
#### JobFactory
- Quartz 스케줄러에서 Job을 인스턴스화하는 역할
- Spring 컨테이너에서 등록된 빈을 가져와 Job 인스턴스를 생성하고 실행 후에는 다시 Spring 컨테이너에 반환하여 재사용 가능하게 함
  - 같은 내용의 Job이어도 인스턴스가 달라야 실행이 가능
    1. 스레드 안전성 확보
    2. Job 실행 상태의 독립성 보장
    3. JobFactory는 트리거가 발동될 때마다 Job 인스턴스를 생성하도록 설계되어 있음
    4. 동시 실행 및 중복 방지
#### SchedulerFactoryBean
- Quartz 스케줄러를 통합하고 관리하는 핵심 컨포넌트
- Quartz의 Scheduler 객체를 생성하고 필요한 설정 적용
  - Spring에 빈으로 등록되어 Spring Context에 의해 생명주기가 관리
  - 다른 컴포넌트에서 의존성 주입(DI)으로 Scheduler 객체를 사용할 수 있음
