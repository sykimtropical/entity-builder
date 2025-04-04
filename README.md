/src/main/java/com/example/entitybuilder/entity 하위 <br>
NoBuilderUser : Getter와 Setter로 Entity 구성 <br>
=> Getter/Setter 만으로 Entity를 구성했을 경우 생길 수 있는 오류 테스트 <br>
<br>
OnlyBuilderUser : @Builder 만으로 Entity 구성 <br>
=> @Builder 만으로 구성 할 경우 Getter, Setter 와 동일하게 필수값이 누락 될 수 있는 경우 오류 테스트 <br>
<br>
ValidBuilderUser : @Builder 에서 필수값을 넣어 Entity 구성 <br>
=> 필수값 없이는 Entity 생성조차 불가능 하도록 제한하는 방식 테스트<br>
<br><br>

/src/test/java/com/example/entitybuilder/EntityBuilderApplicationTests<br>
테스트 수행 시 createUserGetterSetter(), createUserOnlyBuilder() 메서드는 필수값 누락으로 인해 오류 발생<br>
<br>
createUserValidBuilder()는 ValidBuilderUser를 이용하여 엔티티 생성 시 필수값이 제한되어 있기 때문에<br>
컴파일 오류를 이용해 휴먼 오류를 줄일 수 있음.
<br><br>
/src/test/resources/application-tst.yml 내 In-Memory H2 DB를 사용 하였으므로 테스트 바로 실행 가능