# mywebsite
학교에서 구현만하고 코딩하지 못했던 웹페이지를 만들어보자

첫 번째 문제점.
- 철회를 하게되면 버튼이 비활성화 되어야 하는데 계속 활성화가 된 상태로 나온다.
- 다시 생각해본 결과 : 비교구문이 이상하다.
- int cancelNo = cancelDao.cancelList();
- 이렇게 되어있는데 전체 구문을 조회해서 그 값을 인트 하나로 넘긴다는거 자체가 말이 안된다.
- cancelList()가 아니라 cancelSearch(historyNo)로 바꿔야 하는게 맞는 것 같다.
- 
