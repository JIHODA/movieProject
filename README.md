# movieProject
영화예매프로그램(자바 Swing)

Swing Gui를 활용한 자바코어기반 팀프로젝트
영화예매 프로그램 LED CINEMA
프로젝트 기간 : 2023-07-23 ~ 2023-08-01

# 개발환경
O/S : window 10 <br>
IDE : Eclipse <br>
언어 : JAVA jdk 1.8 <br>
데이터베이스 : MariaDB <br>
RestAPI : Login API(KAKAO,NAVER,GOOGLE), Pay API(KAKAO PAY) <br>
기타라이브러리 : MariaDB Client(MariaDB Driver), Lombok, jsoup(데이터크롤링 시 사용) <br>

# 구현설계 및 구동화면
<인트로><br>
팀명 LED를 포함하여 LED CINEMA 라는 인트로 .gif 제작해 구동초기에 삽입
![인트로](https://github.com/JIHODA/movieProject/assets/128888373/71afba38-c05a-4e2d-bcd7-6d3f350a7604)


<메인뷰 와 영화데이터><br>
  CGV 와 메가박스의 페이지화면 등을 참고하여 메인페이지를 구성
  영화는 총 19개 예매순으로 1위~19위의 영화 데이터를 cgv에서 크롤링
  필요한 영화의 데이터는 제목, 예매순위, 감독, 배우, 개봉일, 스토리, 장르, 런닝타임, 포스터 등 과 스틸컷, 연령,성별별 예매분포도
  크롤링 후 데이터베이스에 각 컬럼별로 저장
![메인](https://github.com/JIHODA/movieProject/assets/128888373/632d662c-c167-44ba-89fb-48a0d55431d1)
메인


<회원관리><br>
  회원가입 시 이름, 아이디, 패스워드, 핸드폰번호, 생년월일 과 비밀번호 찾기에 사용할 질문과 답 을 입력받고 DB에 저장
  가입시 중복된 회원인지 체크, 아이디와 비밀번호 길이검증
![회원가입](https://github.com/JIHODA/movieProject/assets/128888373/659c7f1a-05de-4d1f-ab0c-2080a6a4b996)


  로그인은 자체회원과 소셜회원으로 나눠짐 소셜회원은 카카오,구글,네이버 가 있고 각 이메일을 PK로 사용하지만 카카오는 카카오 내부에서 지정된 ID값을 PK로 사용(타 소셜로그인과 이메일중복)
  Swing자체에서 웹뷰를 열어 웹의 통신을 받아 사용하는 방법을 구상했으나 소셜로그인 요청시 HttpServer를 해당 요청시에만 잠시 열어 응답 리디렉션을 콜백함수에서 처리하는 식으로 구현함
  인가코드와 토큰 수령시 까지 while로 기다리며 콜백함수에서 while의 조건 flag를 변경하여 로그인처리 완료 유무를 판단하여 프로그램화면에 출력
![로그인](https://github.com/JIHODA/movieProject/assets/128888373/b95569d4-2bef-48f1-a8df-f848057bcbd2)
로그인
![소셜로그인](https://github.com/JIHODA/movieProject/assets/128888373/d89dd206-24d0-45bb-908b-fd03caa56e41)
소셜로그인

  
<영화디테일><br>
  하나의 디테일페이지를 제작 후 이벤트가 발생된 영화의 데이터를 가져와 출력하게 구현
  디테일페이지에서는 좌측에 포스터 우측에 영화의 대한 정보(제목, 감독, 배우, 스토리 등..)
  하단에는 3개의 메뉴가 있고 기본정보에는 연령,성별별 예매분포도가 있고 스틸컷에는 스틸컷, 한줄평에는 해당영화를 예매한 이력이 있는 회원이 작성 할 수 있게 구현
  예매한 영화 상영시간이 종료되어야 작성하게 구현하려고 하였지만 기대평 or 후기 등을 종합하여 사용 할 수 있게 변경함
  로그인 한 회원은 해당영화를 좋아요 버튼을 눌러 표현할 수 있음
![영화디테일](https://github.com/JIHODA/movieProject/assets/128888373/d808b2c7-382b-4ac9-b505-7d2ddba12490)
기본정보
![영화스틸컷](https://github.com/JIHODA/movieProject/assets/128888373/6c2a1631-d440-4204-b1c9-63789f3c7197)
스틸컷
![한줄평](https://github.com/JIHODA/movieProject/assets/128888373/f07e1eda-9f62-4ccb-bb38-1781d7ec1209)
한줄평
![좋아요](https://github.com/JIHODA/movieProject/assets/128888373/f0e2a6ca-e082-41fc-935f-e69910d2f060)
좋아요

<사전선택 및 좌석선택><br>
  영화 예매 클릭시 사전선택 화면으로 이동 사전 선택화면에서는 날짜, 시간, 인원 등을 선택하여 좌석선택 화면으로 이동
  선택하지않을 시에는 버튼 비활성화
  ![예매사전선택](https://github.com/JIHODA/movieProject/assets/128888373/00ee5a43-0f4d-4684-8ebd-95d93d4ee348)
사전선택

  좌석선택은 전 화면에서 선택된 인원의 수 만큼 좌석을 선택해야함 이미 예매가 진행된 좌석은 비활성화 된 상태로 출력
  마찬가지로 좌석을 모두 선택하지않으면 다음으로 넘어가지못함
  ![좌석선택](https://github.com/JIHODA/movieProject/assets/128888373/e60c89ea-0c39-4a6a-acdc-30def628e22a)
좌석선택

<결제><br>
  결제화면에서는 카드결제, 포인트결제, 카카오페이를 구현하고 있고 카드결제는 카드 자리수 검증, 비밀번호 두자리 입력하면 별도의 검증없이 결제가 이루어지고,
  포인트결제는 포인트로만 결제진행 또는 포인트로 할인을 받아 결제 할 수 있게 설계 (미완성)
  카카오페이는 결제시 카카오페이 결제를 진행 할 수 있는 QR코드가 출력되고 QR코드 인식 시 카카오앱에서 결제를 진행한 후 완료시 프로그램에 결제완료 확인창이 나오고 결제가 완료됨
  ![결제](https://github.com/JIHODA/movieProject/assets/128888373/3bae34d6-ad19-4b03-a1dc-2c1a0d7077b9)\
  결제
  ![카카오페이](https://github.com/JIHODA/movieProject/assets/128888373/6edeaa77-3c22-485d-8866-7ad3a71baad7)
  카카오페이

<내정보><br>
  해당 페이지에서는 회원의 정보, 프로필사진 변경, 한줄평이력, 예매이력 등을 확인 할 수 있고 예매이력의 상세 버튼을 누르면 예매시 미리 캡쳐해 둔 예매완료창 화면을 띄워줌
  영화예매 어플의 현장에서 표 출력 시 QR코드 또는 바코드를 사용하는 것을 보고 구현함
  ![내정보](https://github.com/JIHODA/movieProject/assets/128888373/358993ac-26c1-4f07-9f9b-b377389f3157)
  내정보 프로필사진 변경
  ![예매이력](https://github.com/JIHODA/movieProject/assets/128888373/4d94fbf9-ef98-47d6-9215-fa3335238212)
  예매 이력
  ![예매이력 상세](https://github.com/JIHODA/movieProject/assets/128888373/078fc158-0601-4fdf-87f3-266252aca968)
  예매 상세
