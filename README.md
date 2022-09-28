# InyakSimulator / 인생약탈 시뮬레이터
InyakSimulator 은 GPL 3.0 라이센스를 가지고 있는 오픈소스 플러그인으로, 지인 Vity_ 님의 요청을 제작을 하게되었습니다.

해당 플러그인은 마인크래프트 1.12.2 식 인생약탈 게임구조에 기반하여 시스템을 제공합니다.

* 구동 버전: 마인크래프트 1.12.2 Paper 기반 서버를 지원합니다.

## 다음의 기능을 제공합니다.
아래는 InyakSimulator 가 제공하는 모든 기능을 서술해놓았습니다.

세부 사항(출력 메시지, 시간 요소)은 plugins -> InyakSimulator -> config.yml 에서 설정 가능합니다.

### 1. 편의성 명령어
더이상 essentialsX 를 필요로 하지 않도록, essentialsX 에서 요청받은 필요한 기능을 구현했습니다.

* `/spawn`
스폰 명령어입니다. /setspawn 으로 지정되어 있는 스폰 위치로 이동합니다.

만약, /setspawn 이 되어있지 않으면, world 월드의 0, 60, 0 으로 이동합니다.

이동시간은 기본적으로 5초이며, 콘피그 (Spawn.goTime) 에서 값을 설정 가능합니다.

* `/setspawn`

스폰을 설정하는 명령어입니다. /spawn 명령어로 이동할 위치를 설정합니다.

* `/isreload`

config.yml 파일을 설정하고 나서 플러그인을 다시 시작할 필요없이 바로 새로고침을 합니다.

* `/gm <0,1,2,3> (player)`

플레이어의 게임모드를 설정합니다. 

만약 게임모드 값 뒤에 플레이어의 이름을 적으면, 해당 플레이어의 게임모드를 변경합니다.

0: 서바이벌

1: 크리에이티브

2: 모험모드

3: 관전자모드


* `/fix`

플레이어 보관함에 있는 모든 아이템 중 내구도가 감소되어있는 아이템을 수리합니다.

* `/s <player>`

입력한 플레이어를 명령어 사용자의 위치로 이동시킵니다.

* `/tp <player> (player)`

입력한 플레이어의 위치로 이동합니다. 

만약 추가 플레이어를 입력시 첫번째로 입력한 플레이어를 두번째로 입력한 플레이어의 위치로 이동시킵니다.

예시) /tp DDang_ Vity_ -> DDang_ 플레이어를 Vity_ 플레이어의 위치로 이동시킴

* `/setwarp <이름>`

  입력한 이름으로 해당 위치를 워프로 만듭니다.

* `/warp <이름>`

  입력한 이름의 워프로 이동합니다.

* `/info <이름>`

입력한 플레이어의 장비 (투구, 흉갑, 각반, 신발, 주로 사용하는 손에 든 아이템) 의 이름을 채팅으로 출력합니다.,

또한 해당 플레이어 장비의 모든 스탯의 총합 값을 표기합니다.


### 2. 아이템 로어를 이용한 스탯 효과

아이템 로어에 스탯 이름과 스탯 적용값을 입력하여 스탯 효과를 받을 수 있습니다.

로어 한 줄에 스탯 이름이 있으면 그 줄에 있는 숫자를 스탯 적용값으로 인식합니다. 

(띄어쓰기로 각 단어를 구분합니다. 스탯 이름 혹은 다른 단어와 스탯 적용값이 붙여져있으면 인식되지 않습니다.)

예시:

공격력 1 -> 공격력 1 증가

공격력 +1 -> 공격력 1 증가

공격력: 1 증가 -> 공격력 1 증가 

공격력1증가 -> 적용 안됨

공격력: 1증가 -> 적용 안됨

음수값은 허용되지 않습니다.

<사용할 수 있는 스탯>

#### 1. 공격력

스탯 적용값 1당 다른 엔티티에게 가하는 피해량이 1.0 (반 칸) 증가합니다.

예시) 공격력 2 -> 피해량 2 증가 (한 칸)

#### 2. 방어력

스탯 적용값 1 당 피해량을 1% 감소시킵니다.

100을 넘어갈 수 없으며 100 은 모든 피해를 막습니다. (피격 이펙트 및 넉백은 주기 위해 피해량을 0.0001로 설정합니다.)

예시) 방어력 50 -> 피해량 50% 감소 (절반)

#### 3. 체력

스탯 적용값 1당 자신의 최대 체력을 1.0 (반 칸) 증가시킵니다.

예시) 체력 2 -> 최대 체력 1칸 증가

#### 4. 흡혈률

스탯 적용값 1당 1% 의 흡혈 확률을 갖습니다.

상대를 타격했을 때 발동되며 100을 넘어갈 수 없습니다.

예시) 흡혈률 10 -> 10% 확률로 흡혈 터짐

흡혈률 10% 로 표기 가능

#### 5. 흡혈량

스탯 적용값 1당 1.0 의 흡혈량을 갖습니다. (반 칸)

상대를 타격했을 때 흡혈률에 의해 흡혈이 발생하면, 흡혈량만큼 체력을 회복합니다.

예시) 흡혈량 2 -> 흡혈 터질때 체력 1칸 회복
