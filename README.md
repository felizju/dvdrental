
# 오라클 com.funnydvd.dvdrental.jdbc 라이브러리 (ojdb6.jar)
- 파일 경로 'C:\oraclexe\app\oracle\product\11.2.0\server\com.funnydvd.dvdrental.jdbc\lib'
- 프로젝트 src/main/webapp/WEB-INF/lib에 ojdbc6.jar 복사
- build.gradle에 아래 코드 추가
  (maven : form.xml 파일)
  
  ```groovy
    // 라이브러리 의존성 관리 (라이브러리 다운로드 설정, 로딩 설정)
    dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.6.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine'
  
    // 오라클 com.funnydvd.dvdrental.jdbc 라이브러리 설정
    compile fileTree(dir: '/src/main/webapp/WEB-INF/lib', includes: ['*.jar'])
    }
  ```