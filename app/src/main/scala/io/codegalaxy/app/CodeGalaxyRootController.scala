package io.codegalaxy.app

import io.codegalaxy.app.user.{UserActions, UserController}
import io.github.shogowada.scalajs.reactjs.redux.Redux.Dispatch
import scommons.react._
import scommons.react.navigation._
import scommons.reactnative.app.BaseStateAndRouteController

class CodeGalaxyRootController(onAppReady: () => Unit,
                               actions: UserActions,
                               userController: UserController)
  extends BaseStateAndRouteController[CodeGalaxyStateDef, CodeGalaxyRootProps] {

  lazy val uiComponent: UiComponent[CodeGalaxyRootProps] = new CodeGalaxyRoot(userController)

  def mapStateAndRouteToProps(dispatch: Dispatch,
                              state: CodeGalaxyStateDef,
                              nav: Navigation): CodeGalaxyRootProps = {
    CodeGalaxyRootProps(
      dispatch = dispatch,
      actions = actions,
      state = state.userState,
      onAppReady = onAppReady
    )
  }
}
