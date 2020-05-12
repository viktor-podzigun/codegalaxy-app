package io.codegalaxy.app.user

import io.github.shogowada.scalajs.reactjs.redux.Redux.Dispatch
import scommons.react._
import scommons.react.navigation._
import scommons.react.navigation.stack._
import scommons.reactnative._

import scala.scalajs.js

case class UserScreenProps(dispatch: Dispatch,
                           actions: UserActions,
                           data: UserState)

object UserScreen extends FunctionComponent[UserScreenProps] {
  
  protected def render(compProps: Props): ReactElement = {
    val props = compProps.wrapped

    def renderField(name: String, value: Option[String]): ReactElement = {
      <.View(^.rnStyle := styles.fieldRow)(
        <.View(^.rnStyle := styles.fieldContainer)(
          <.Text(^.rnStyle := styles.fieldName)(s"$name:")
        ),
        <.View(^.rnStyle := styles.valueContainer)(
          <.Text(^.rnStyle := styles.fieldValue)(s"${value.getOrElse("")}")
        )
      )
    }
    
    <.View()(
      props.data.profile.map { data =>
        <.>()(
          renderField("User Name", Some(data.username)),
          renderField("First Name", data.firstName),
          renderField("Last Name", data.lastName),
          renderField("City", data.city)
        )
      }
    )
  }

  private[user] lazy val Stack = createStackNavigator()

  def userStackComp(userController: UserController): ReactClass = new FunctionComponent[Unit] {
    protected def render(props: Props): ReactElement = {
      <(Stack.Navigator)(^.initialRouteName := "Profile")(
        <(Stack.Screen)(^.name := "Profile", ^.component := userController())()
      )
    }
  }.apply()

  private[user] lazy val styles = StyleSheet.create(new Styles)
  private[user] class Styles extends js.Object {
    import TextStyle._
    import ViewStyle._

    val fieldRow: Style = new ViewStyle {
      override val flexDirection = FlexDirection.row
      override val marginTop = 10
    }
    val fieldContainer: Style = new ViewStyle {
      override val flex = 1
      override val alignItems = AlignItems.`flex-end`
    }
    val valueContainer: Style = new ViewStyle {
      override val flex = 1
      override val alignItems = AlignItems.`flex-start`
    }
    val fieldName: Style = new TextStyle {
      override val fontWeight = FontWeight.bold
      override val marginRight = 10
    }
    val fieldValue: Style = new TextStyle {
      override val fontStyle = FontStyle.italic
    }
  }
}