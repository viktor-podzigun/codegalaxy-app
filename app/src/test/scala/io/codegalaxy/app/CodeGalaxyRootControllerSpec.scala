package io.codegalaxy.app

import io.github.shogowada.scalajs.reactjs.redux.Redux.Dispatch
import scommons.react.navigation.Navigation
import scommons.react.test.TestSpec

class CodeGalaxyRootControllerSpec extends TestSpec {

  it should "return component" in {
    //given
    val actions = mock[CodeGalaxyActions]
    val controller = new CodeGalaxyRootController(onAppReady = () => (), actions)
    
    //when & then
    controller.uiComponent.isInstanceOf[CodeGalaxyRoot] shouldBe true
  }
  
  it should "map state to props" in {
    //given
    val dispatch = mock[Dispatch]
    val actions = mock[CodeGalaxyActions]
    val onAppReady = mockFunction[Unit]
    val controller = new CodeGalaxyRootController(onAppReady, actions)
    val state = mock[CodeGalaxyStateDef]
    val nav = mock[Navigation]

    //when
    val result = controller.mapStateAndRouteToProps(dispatch, state, nav)
    
    //then
    inside(result) {
      case CodeGalaxyRootProps(resDispatch, resActions, resState, resOnAppReady) =>
        resDispatch shouldBe dispatch
        resActions shouldBe actions
        resState shouldBe state
        resOnAppReady shouldBe onAppReady
    }
  }
}
