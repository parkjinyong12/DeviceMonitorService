# DeviceMonitorService
디바이스 모니터링 서비스 기획중

데이터 수집기능은 아직 구현되어 있지 않습니다.
- 상용 디바이스 구매 후 해당 디비이스에서 제공하는 데이터 형식에 따라 데이터 수집, 저장기능 구현 예정. 

Spring security 로그인 기능 추가. 메인화면 진입시, 사용자 Role에 따라 다른 메뉴를 제공합니다.  
(현재 Role: 관리자, 제조사, 판매사, 일반사용자)  

개별 주체별 필요 기능 추가 중  
  
기능  
- 디바이스 제조사 관리(화면)  
- 디바이스 판매사 관리(화면)  
- 디바이스 모델 관리(화면)  
- 유심정보 관리(화면)  
- 디바이스 재고 관리(화면)  
- 디바이스 설치 관리(화면)  
  
비기능  
- AOP활용 서비스 진입, 종료 시 로그 출력  
- 서비스 단위 트랜잭션 관리
- DB Connection Pool 관리(예정)  
- Circle Ci를 활용한 배포자동화(예정)

화면은 현재 JSP -> Vue.js로 변경을 고려하고 있습니다.



