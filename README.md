# 📞시시콜콜 (SeeSeeCallCall)
친구들의 MBTI를 보고(See), 통화(Call)할 수 있는 연락처 어플

<a href="https://github.com/SeeSeeCallCall/SeeSeeCallCall/actions/new">
      <img alt="Tests Passing" src="https://img.shields.io/github/languages/top/SeeSeeCallCall/SeeSeeCallCall?style=flat&logo=kotlin&logoColor=white&color=800080" />
</a>
<a href="https://github.com/SeeSeeCallCall/SeeSeeCallCall/commits/dev/">
      <img alt="Tests Coverage" src="https://img.shields.io/github/commit-activity/m/SeeSeeCallCall/SeeSeeCallCall" />
</a>
<a href="https://github.com/SeeSeeCallCall/SeeSeeCallCall">
      <img alt="GitHub Contributors" src="https://img.shields.io/github/contributors/SeeSeeCallCall/SeeSeeCallCall" />
</a>
<a href="https://github.com/SeeSeeCallCall/SeeSeeCallCall/pulls">
      <img alt="GitHub pull requests" src="https://img.shields.io/github/issues-pr/SeeSeeCallCall/SeeSeeCallCall?color=red" />
</a>

## 🎯Developers

|이름|github link|
|:---|:---|
이선주|🔗[sunjoolee](https://github.com/sunjoolee)
김나희|🔗[kimnahee1529](https://github.com/kimnahee1529)
황현우|🔗[hwoo4013](https://github.com/hwoo4013)
문혜린|🔗[PEPTIDE707](https://github.com/PEPTIDE707)

## 🎯Wire Frame
[🔗Figma](https://www.figma.com/file/VLahiXW3Gkmyh3kz6pVL68/Untitled?type=design&node-id=0%3A1&mode=design&t=SPQGwQdQO2axDYgr-1)
![image](https://github.com/SeeSeeCallCall/SeeSeeCallCall/assets/86330886/02d40582-6559-4c12-89b8-1a3421378e04)
![image](https://github.com/SeeSeeCallCall/SeeSeeCallCall/assets/86330886/7193e45e-ba7b-4e84-b5b9-ae63dd52669c)


## 🎯Implemented Features
### (0) 전체 앱

- `BottomNavigationView`로 메인 화면(연락처)/즐겨찾기 화면/마이 페이지 화면 이동
- `ViewPager`를 이용해 좌우 스와이프로 메인 화면(연락처)/즐겨찾기 화면/마이 페이지 화면 이동

### (1) 연락처 목록 프래그먼트

- 검색 기능
    - 이름, mbti로 연락처 검색 가능
- 즐겨찾기 연락처 목록 표시
    - `RecyclerView`로 구현
    - 즐겨찾기 연락처 배너 클릭 시, 즐겨찾기 연락처 목록(화면 최상단)으로 스크롤
- 연락처 목록 표시
    - 연락처 이름이 시작하는 글자으로 그룹핑되어 연락처 목록 표시
        - 한글 자음, 영어, 숫자, 특수문자로 그룹핑
    - `Recyclerview in Recyclerview`로 구현
    - 연락처 목록의 각 항목에 연락처 정보 표시
        - 즐겨찾기 여부 (별 아이콘)
        - 프로필 이미지
            - 프로필 이미지가 없는 경우, 친구의 MBTI에 해당하는 일러스트 표시
            - 친구의 MBTI를 모르는 경우, 기본 이미지 표시
        - 이름
        - 연락처
        - MBTI 태그 (친구의 MBTI와 나와의 MBTI 궁합을 색으로 표시)
- 연락처 상세 보기 기능
    - 연락처를 클릭하면, 해당 연락처의 상세 화면으로 이동
- 연락처 추가 기능
    - 화면 하단 플로팅 액션 버튼을 클릭하면, 연락처 추가 프래그먼트 표시
- 즐겨찾기 기능
    - 회색 별 아이콘을 누르면, 해당 연락처 즐겨찾기 연락처 목록에 추가
    - 노란 별 아이콘을 누르면, 해당 연락처 즐겨찾기 연락처 목록에서 삭제.

### (2) 연락처 상세 프래그먼트

- 전체 화면 `ScrollView`로 구현
- 메인 화면에서 `Bundle`로 전달받은 `Parcable` 연락처 객체를 이용해 연락처 정보 표시
    - 즐겨찾기 여부 별 아이콘으로 표시
    - 프로필 사진/MBTI 태그/이름/전화번호/이메일/생년월일
- 즐겨찾기 기능
    - 회색 별 아이콘을 누르면, 해당 연락처 즐겨찾기 연락처 목록 추가
    - 노란 별 아이콘을 누르면, 해당 연락처 즐겨찾기 연락처 목록에서 삭제
- `Intent`를 활용한 메시지/통화 버튼 기능 구현
    - 메세지 버튼 클릭 시, 해당 번호로 메세지 전송 화면으로 이동
    - 전화 버튼 클릭 시, 해당 번호 다이얼 화면으로 이동
- 뒤로 가기 아이콘을 누르면 이전 화면(연락처 목록 프래그먼트) 으로 돌아감
- 연락처 삭제 기능
    - ‘연락처 삭제’  텍스트  클릭 시, 연락처 삭제 다이얼로그 표시

### (2’) 연락처 삭제 다이얼로그

- 삭제 확인 클릭 시, 연락처 삭제 후 메인 화면(연락처 목록 프래그먼트)로 이동

### (2’’) 궁합 보기 다이얼로그

- 연락처에 저장된 MBTI의 설명 및 MBTI 궁합 표시
    - 천생연분 궁합, 좋은 궁합, 최악 궁합
- 연락처에 저장된 MBTI와 나의 MBTI와의 궁합 표시
    - “나와의 궁합은 천생연분/좋음/무난/최악 입니다”

### (3) 연락처 추가 프래그먼트

- 사용자 정보 입력 받기
    - `Spinner`를 통해 MBTI 입력받기
    - `EditTextView`를 이용해 이름/전화번호/이메일 입력받기
        - `PhoneNumberFormattingTextWatcher` 를 사용해 전화번호 입력 시, 자동으로 하이픈 입력되도록 구현
    - DatePicker를 통해 생년월일 입력받기
- 사용자 프로필 사진 입력받기
    - 사용자 프로필을 클릭하면, 갤러리에서 사진을 불러올 수 있음
    - 사용자 프로필을 한번 더 클릭하면, 불러온 사진 삭제됨
    - 프로필 사진을 불러오지 않은 경우, MBTI에 맞는 이미지로 프로필 설정됨
- 입력 칸 유효성 검사 기능
    - 이름이 공란인 경우,  전화번호 형식이 맞지 않는 경우, 이메일 형식이 맞지 않는 경우, 에러 메세지 표시 (`Regex`를 이용해 구현)
    - 모든 입력이 유효하면, 확인 버튼을 눌렀을 때 새로운 연락처 생성되고 메인 화면(연락처 목록 프래그먼트)로 돌아감

### (4) 즐겨찾기 화면

- 즐겨찾기된 연락처들 `RecyclerView`로 표시
    - Grid 레이아웃으로 배치
- 검색 기능
    - 연락처 이름/MBTI로 검색 가능
- 연락처 항목 클릭 시, 해당 연락처 다이얼 화면으로 바로 이동
- 연락처 항목 별 아이콘 클릭 시, 즐겨찾기 목록에서 연락처 삭제

### (5) 마이 페이지 화면

- 내 프로필 사진/MBTI/이름/전화번호/이메일/생년월일 표시
- 내 정보 수정하기 버튼클릭 시, 연락처 추가 프래그먼트를 통해 내 정보 수정 가능
- 검사하기 버튼 클릭 시, MBTI 테스트 웹페이지로 바로 이동
 
