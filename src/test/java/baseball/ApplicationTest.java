package baseball;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 게임종료_후_재시작() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("246", "135", "1", "597", "589", "2");
                    assertThat(output()).contains("낫싱", "3스트라이크", "1볼 1스트라이크", "3스트라이크", "게임 종료");
                },
                1, 3, 5, 5, 8, 9
        );
    }

    @Test
    void 게임종료() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("246", "135", "2");
                    assertThat(output()).contains("낫싱", "3스트라이크", "게임 종료");
                },
                1, 3, 5
        );
    }

    @Test
    void 예외_테스트_사용자_입력_3자리_숫자() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1234"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_사용자_입력_문자_포함() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("12a"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_사용자_입력_범위() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("012"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_사용자_입력_서로_다른_숫자() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("131"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 게임종료_후_재시작_예외_테스트_사용자_입력() {
        try {
            assertRandomNumberInRangeTest(
                    () -> {
                        run("456", "123", "1", "23597", "589", "2");
                        assertThat(output()).contains("낫싱", "3스트라이크", "1볼 1스트라이크", "3스트라이크", "게임 종료");
                    },
                    1, 2, 3, 5, 8, 9
            );
        } catch (IllegalArgumentException E){
            System.out.println("잘못된 입력입니다.");
        }
    }

    @Test
    void 게임종료_후_재시작_예외_테스트_사용자_입력_1과_2() {
        try {
            assertRandomNumberInRangeTest(
                    () -> {
                        run("456", "123", "9");
                        assertThat(output()).contains("낫싱", "3스트라이크", "게임 종료");
                    },
                    1, 2, 3, 5, 8, 9
            );
        } catch (IllegalArgumentException E){
            System.out.println("잘못된 입력입니다.");
        }
    }
    
    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
