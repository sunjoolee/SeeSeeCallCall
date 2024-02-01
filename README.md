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
[🔗Figma](https://www.figma.com/file/VLahiXW3Gkmyh3kz6pVL68/Untitled?type=design&node-id=0면

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
 
