package io.codegalaxy.app

import io.codegalaxy.app.user.UserActions.UserLoginAction
import io.codegalaxy.app.user.{UserLoginState, UserState}
import scommons.react.redux.task.FutureTask
import scommons.react.test.TestSpec

import scala.concurrent.Future

class CodeGalaxyStateReducerSpec extends TestSpec {

  it should "return initial state" in {
    //when
    val result = CodeGalaxyStateReducer.reduce(None, "")

    //then
    inside(result) {
      case CodeGalaxyState(
        currentTask,
        userState
      ) =>
        currentTask shouldBe None
        userState shouldBe UserState()
    }
  }

  it should "set currentTask when TaskAction" in {
    //given
    val initialState = CodeGalaxyStateReducer.reduce(None, "")
    val task = FutureTask("test task", Future.successful(Option(mock[UserLoginState])))
    initialState.currentTask shouldBe None

    //when
    val result = CodeGalaxyStateReducer.reduce(Some(initialState), UserLoginAction(task))

    //then
    result.currentTask shouldBe Some(task)
  }
}
