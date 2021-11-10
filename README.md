# mywebsite
학교에서 구현만하고 코딩하지 못했던 웹페이지를 만들어보자

첫 번째 문제점.
- 철회를 하게되면 버튼이 비활성화 되어야 하는데 계속 활성화가 된 상태로 나온다.
- 다시 생각해본 결과 : 비교구문이 이상하다.
- int cancelNo = cancelDao.cancelList();
- 이렇게 되어있는데 전체 구문을 조회해서 그 값을 인트 하나로 넘긴다는거 자체가 말이 안된다.
- cancelList()가 아니라 cancelSearch(historyNo)로 바꿔야 하는게 맞는 것 같다.
- 주소창에 검색해서 반복접근하지 못하도록 서블릿에서 대책을 강구해야 할 것 같다.

첫 번째 문제점 해결 완료
---> 해결방법. 받아온 historyNo가 cancel테이블에 있으면 철회 링크를 보여주지 않고
              없으면 링크를 보여준다.
              철회 시 cancel테이블에 historyNo와 memberId(쓰진않음)를 받아서 저장한다.
              cancelDao에 historyNo를 이용해 조회하는 메소드를 만들었다.
              history foreach문에 cancelDto = cancelDao.cancelSearch(historyDto.getHistoryNo())를 만들었고
              cancelDto == null이고 historyDto.getHistoryMemo().equals("포인트 구입") 이면 철회 링크를 보여주게 끔 만들었다.
              

첫 번째 개선안.
  - db에 api를 적용시킬 줄을 모르니 그거 하는방법 찾느라 시간버릴바에
  - 그냥 주소를 도(선택), (시, 구, 동(필수)) 이렇게 각각 입력받고 서블릿에서 합쳐서 세터로 저장한 뒤 디비로 보내자.
  - 검색은 도, 시, 구, 동을 검색할 수 있게 하면 되지않을까?
  - > 이건 쿼리문을 잘 짜보자. (지금은 건들지 않겠다. 추후에 건들게되면 그때 이 줄을 지우겠음)
