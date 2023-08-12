### 쓰면서 제대로 공부하기

<aside>
💡 Duck의 행동을 상속할 때 단점이 될 수 있는 요소

1. 서브클래스에서의 코드 중복
2. 코드 변경시 다른 오리들에게 원치 않는 영향을 끼칠 수 있음.
</aside>

<aside>
💡 애플리케이션을 만드는 과정에서 코드를 바꿔야 했던 이유?

1. 애플리케이션의 성능 개선
2. 백엔드에서 데이터 전달 양식의 변화
</aside>

<aside>
💡 앞 쪽에 나온 디자인을 활용해 SimUDuck에 로켓의 추진력으로 날아가는 행동을 추가하려면?

FlyBehavior 인터페이스를 구현하는 FlybyRocket 클래스를 생성한다.

</aside>

<aside>
💡 오리 클래스가 아닌 다른 클래스에서 Quack을 활용하는 방법?

다른 클래스가 QuackBehavior을 자식으로 가지도록 한다.

</aside>

## 소프트웨어 개발 불변의 진리

1. 아무리 디자인을 잘한 애플리케이션이라도 시간이 지남에 따라 변화하고 성장해야 함.

## 디자인 원칙

1. 애플리케이션에서 달라지는 부분을 찾아내고, 달라지지 않는 부분과 분리한다.

⇒ 달라지는 부분을 찾아서 나머지 코드에 영향을 주지 않도록 “캡슐화”한다.
그러면 코드를 변경하는 과정에서 의도치 않게 발생하는 일을 줄이면서
시스템의 유연성을 향상 시킬 수 있다.

### “인터페이스에 맞춰서 프로그래밍한다” === “상위 형식에 맞춰서 프로그래밍한다”

: 핵심은 실제 실행 시에 쓰이는 객체가 코드에 고정되지 않도록 상위 형식(supertype)에 맞춰 프로그래밍해서 다형성을 활용해야 한다는 점에 있다.

”상위 형식에 맞춰서 프로그래밍하라”는 원칙은 “변수를 선언할 때 보통 추상 클래스나 인터페이스 같은 상위 형식으로 선언해야한다. 객체를 변수에 대입할 때 상위 형식을 구체적으로 구현한 형식이면 어떤 객체든 넣을 수 있기 때문.
그러면 변수를 선언하는 클래스에서 실제 객체의 형식을 몰라도 됨”

`**ex` 다형적인 형식을 사용하는 예시**

- Animal이라는 추상 클래스 밑에 Dog, Cat이라는 구상 클래스가 있다고 가정하면 구현에 맞춰 아래와 같이 프로그래밍 할 수 있음

```java
Dog d = new Dog();
d.bark();
```

변수 d를 Dog 형식 (animal을 확장한 구상 클래스)로 선언하면
구체적인 구현에 맞춰서 코딩해야 함

⇒ 인터페이스와 상위 형식에 맞춰서 프로그래밍 한다면

```java
Animal animal = new Dog();
animal.makeSound();
```

Dog라는 걸 알고 있긴 하지만 다형성을 활용해서
Animal의 레퍼런스를 사용해도 됨.

⇒ 더 바람직한 방법은 상위 인스턴스를 만드는 과정을 (new Dog() 같은 식으로) 직접 코드로 만드는 대신 구체적으로 구현된 객체를 실행 시에 대입하는 것

```java
a = getAnimal();
a.makeSound();
```

Animal의 하위 형식 가운데 어떤 형식인지는 모름
단지 makeSound()에 올바른 반응만 할 수 있으면 됨

## 오리 행동의 통합

- 날아다니는 행동과 꽥꽥거리는 행동을 Duck 클래스에서 정의한 메소드를 써서
  구현하지 않고 다른 클래스에 `“위임”` 함

### 생각해보시오

1. **Duck 클래스에 flyBehavior와 quackBehavior이라는 인터페이스 형식의 인스턴스 변수 추가**
    1. 각 오리 객체에서는 실행 시에 이 변수에 특정 행동 형식 (FlyWithWings, Squeak 등)의 레퍼런스를 다형적으로 설정함.
    2. 나는 행동과 꽥꽥거리는 행동은 FlyBehavior와 QuackBehavior 인터페이스로 옮겼으므로 Duck 클래스와 모든 서브클래스에서 fly()와 quack() 메소드를 제거함.
    3. Duck 클래스 fly()와 quack 대신 performFly()와 performQuack()이라는 메소드를 넣음

    ```mermaid
    classDiagram
        class Duck {
    			FlyBehavior flyBehavior
    			QuakBehavior quackBehavior
          performQuack()
    			swim()
    			display()
    			performFly()
    			// 기타 오리 관련 메소드()
        }
    ```

    - 행동 변수는 행동 인터페이스 형식으로 선언
    - fly() 와 quack() 대신 performQuack(), performFly()가 들어감
    - 인스턴스 변수에는 실행 시에 특정 행동의 레퍼런스가 저장 됨
        - 나는 행동, 꽥꽥 거리는 행동
    1. performQuack() 구현

    ```jsx
    public abstract class Duck {
    //모든 duck에는 QuackBehavior 인터페이스를 구현하는 것의 레퍼런스가 있음
    	QuackBehavior quackBehavior;
    	// 기타 코드
    
    	public void performQuack() {
    // 꽥꽥거리는 행동을 직접 처리하는 대신, quackBehavior로 참조되는 객체에 그 행동을 위임함.
    		quackBehavior.quack();
    	}
    }
    ```

   ⇒ 꽥꽥 거리는 행동을 하고 싶을 땐 quackBehavior에 의해 참조되는 객체에서 꽥꽥거리도록 하면 됨. 객체의 종류에는 상관 X

    1. flyBehavior과 quackBehavior 인스턴스 변수 설정 방법 생각해보기

        ```jsx
        Public class MallarDuck extends Duck {
        
        // MallardDuck은 Duck클래스에서 quackBehavior와 flyBehavior 인스턴스 변수 상속 받음
        	public MallarDuck() {
        // MallardDuck이 꽦 거리는 행동 처리시엔 Quack 클래스를 쓰므로 performQuack() 호출시 꽥 행동은 Quack객체에 위임
        		quackBehavior = new Quack();
        // FlyBehavior 형식으로는 FlyWithWings 사용
        		flyBehavior = new FlyWithWings();
        }
        
        public void display() {
        		System.out.println("저는 물오리입니다");
        	}
        }
        ```

        - MallarDuck에서는 소리 내지 못하거나 삑삑 소리만 내는 오리가 아닌 진짜 꽥꽥 소리를 내는 오리 구현가능
            - MallardDuck의 인스턴스 변수에
              Quack(QuackBehavior을 구현한 구상 클래스) 형식의 새로운 인스턴스 대입

   ## 오리 코드 테스트

   https://github.com/designPatternStudyJB/HEAD_FIRST

    1. Duck.java와 앞에 있던 [MallarDuck.java](http://MallarDuck.java) 입력해서 컴파일 해보기

    ```jsx
    public abstract calss Duck {
    		FlyBehavior flyBehavior;
    		QuackBEhavior quackBehavior;
    
    		public Duck() { }
    
    		public abstract void display();
    
    		public void performFly() {
    				flyBehavior.fly();
    		}
    
    		public void performQuack() {
    			quackBehavior.quack();
    		}
    
    		public void swim() {
    			System.out.println("모든 오리는 물에 뜹니다. 가짜 오리도 뜨죠");
    		}
    }
    ```

2. FlyBehavior 인터페이스(FlyBehavior.java)와 행동 구현 클래스 (FlyWithWings.java, FlyNoWay.java)를 입력해서 컴파일하기

```jsx
public interface FlyBehavior {
		public void fly();
	}
// 모든 나는 클래스에서 구현하는 인터페이스
```

```jsx
public class FlyWithWings implements FlyBehavior {
		public void fly() {
			System.out.println("날고 있어요!!");
	}
}

// 실제로 날 수 있는 오리들의 나는 행동을 구현한 클래스
```

```jsx
public class FlyNoWay implements FlyBehavior {
		public void fly() {
			System.out.println("저는 못 날아요");
	}
}

// 날 수 없는 오리들의 나는 행동을 구현한 클래스
```

1. QuackBehavior 인터페이스 (QuackBehavior.java)와 3개의 행동 구현 클래스 (Quack.java [MuteQuack.java](http://MuteQuack.java), Squeak.java) 입력해서 컴파일 하기

```jsx
public interface QuackBehavior {
		public void quack();
}
```

```jsx
public class Quack implements QuackBehavior {
		public void quack() {
				System.out.println("삑");
	}
}
```

```java
public class MuteQuack implements QuackBehavior {
		public void quack() {
				System.out.println("<< 조용 ~ >>");
	}
}
```

```java
public class Squeak implements QuackBehavior {
		public void quack() {
				System.out.println("삑");
	}
}
```

1. 테스트 클래스 (MiniDuckSimoulator.java)를 입력해서 컴파일 해보기

```jsx
public class MiniDuckSimulator {
		public static void main(Stirng[] args) {
			Duck mallard = new MallmardDuck();
			mallard.performQuack();
			mallard.performFly();
		}
}
```

## 동적으로 행동 지정하기

1. Duck클래스에 메소드 2개 새로 추가

```jsx
public void setFlyBehavior(FlyBehavor fb) {
		flyBehavior = fb;
}

public void setQuackBehavior(QuackBehavior qb) {
		quackBehavior = qb;
}
```

1. Duck의 서브클래스(ModelDuck.java) 새로 만들기

```jsx
public class ModelDuck extends Duck {
	public ModelDuck() {
		flyBehavior = new FlyNoWay();
		quackBehavior = new Quack();
	}

	public void display() {
		System.out.println("저는 모형 오리입니동");
	}
}
```

1. FlyBehavior 형식의 클래스(FlyRocketPowered.java)를 새로 만들기

```jsx
public class FlyRocketPowered implements FlyBehavior {
public void fly() {
		System.out.println("로켓 추진으로 날아갑니다");
	}
}
```

1. 테스트 클래스(MiniDuckSimulator) 수정
   MoodelDuck 추가 후 ModelDuck에 로켓 추진 기능 부여

```java
public class MiniDuckSimulator {
		public static void main(Stirng[] args) {
			Duck mallard = new MallmardDuck();
			mallard.performQuack();
			mallard.performFly();

			Duck model = new ModelDuck();
			model.performFly();
			model.setFlyBehavior(new FlyRocketPowered());
			model.performFly();
		}
}
```

⇒ 실행 중에 오리의 행동을 바꾸고 싶다면 원하는 행동에 해당하는 duck의 setter 메소드 호출하기

## 캡슐화된 행동 살펴보기 : 캡슐화된 행동을 큰 그림으로 바라보기

- 오리의 행동들을 일련의 행동으로 생각하는 대신, 알고리즘군으로 생각하기
- 클래스 사이에 관계에도 관심 기울이기
    - 클래스 다이어그램에 있는 각 화살표와 클래스들이 어떤 관계인지 직접 써보자
        - A에는 B가 **`있다`** 관계인지 ? 아니면 A가 B를 **`구현하는`** 관계인지


# 두 클래스 합치기

: A는 B이다 보다 A에는 B가 있다가 더 나을 수 있음!!

두 클래스를 함치는 것을 `구성(composition)을 이용한다` 라고 부름
오리 클래스에서는 행동을 상속 받는 대신, 올바른 객체로 구성되어 행동을 부여 받음

구성을 활용하면 유연성을 크게 향상할 수 있음

1. 단순히 알고리즘군을 변도의 클래스 집합으로 캡슐화 가능
2. 구성 요소로 사용하는 객체에서 올바른 행동 인터페이스를 구현하기만 하면 실행 시에 행동 변경도 가능

이게 바로

# 전략 패턴

<aside>
💡 전략패턴 (strategy pattern)

알고리즘군을 정의하고 캡슐화해서 각각의 알고리즘군을 수정해서 쓸 수 있게 해줌

전략 패턴을 사용하면 클라이언트로부터 알고리즘을 분리해서 독립적으로 변경 가능!

</aside>

## 패턴과 전문 용어 : 디자인 패턴을 사용하면 어떤 이득?

⇒ 효율적인 의사소통 가능

1. 패턴으로 의소통하면 패턴 이름과 그 패턴에 담긴 애용 특성 제약 조건들에 대해서 함께 말할 수 있음.
   ( “ 오리들의 다양한 행동을 전략 패턴으로 구현하고 있습니다” 라고 말한다면
   이는 오리의 행동들을 쉽게 확장하거나 변경할 수 있는 클래스들의 집합으로 캡슐화되어 있다는 사실을 의미, : 필요하다면 실행 중에도 확장과 변경이 가능하도록 ! )
2. 간단한 언어로 많은 이야기 가능 ! ⇒ 생각하고 있는 디자인을 다른 개발자가 빠르고 정확하게 파악 가능
3. 객체와 클래스를 구현하는 것과 관련된 자질구레한 내용에 시간을 버릴 필요가 없어 디자인 수준에서 초점을 맞출 수 있음
4. 오해의 소지가 줄어 작업을 빠르게 진행할 수 있음 ⇒ 개발팀의 능력 극대화

### 객체 지향 기초

1. 추상화
2. 캡슐화
3. 다형성
4. 상속

### 객체 지향 원칙

1. 바뀌는 부분은 캡슐화
2. 상속보다는 구성 활용
3. 구현보다는 인터페이스에 맞추어 프로그래밍

### 객체 지향 패턴

`전략패턴`

: 알고리즘군을 정의하고 캡슐화해서 각각의 알고리즘군을 쓸 수 있게 해줌.
전략 해턴을 사용하면 클라이언트로부터 알고리즘을 분리해서 독립적으로 변경 가능