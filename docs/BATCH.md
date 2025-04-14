# Quartz Scheduler를 이용한 Spring Batch Job 관리

---
## Quartz 흐름도
![Quartz흐름도.jpg](Quartz%ED%9D%90%EB%A6%84%EB%8F%84.jpg)

### AutowiringSpringBeanJobFactory
- Spring Job의 의존성 주입을 Quartz Job에 적용하기 위한 커스텀 JobFactory
  - 개발자는 Spring Job을 구성하고 Bean으로 생성
  - Quartz Scheduler를 이용하기 위해서는 Quartz Job Instance가 필요
  - <span style="background-color:#fff5b1"> 자동으로 Spring Job Bean을 이용해 Quartz Job Bean 생성하기 위한 JobFactory </span>
- 
