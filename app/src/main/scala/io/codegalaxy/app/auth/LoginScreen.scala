package io.codegalaxy.app.auth

import io.codegalaxy.app.CodeGalaxyTheme
import scommons.react._
import scommons.react.hooks._
import scommons.reactnative.KeyboardAvoidingView._
import scommons.reactnative.ScrollView._
import scommons.reactnative.TextInput._
import scommons.reactnative._

import scala.scalajs.js

case class LoginScreenProps(onLogin: (String, String) => Unit,
                            onSignup: () => Unit)

object LoginScreen extends FunctionComponent[LoginScreenProps] {

  protected def render(compProps: Props): ReactElement = {
    val (email, setEmail) = useState("")
    val (password, setPassword) = useState("")
    val props = compProps.wrapped
    val disabled = !email.contains('@') || password.isEmpty

    <.KeyboardAvoidingView(
      ^.rnStyle := styles.container,
      ^.behavior := {
        if (Platform.OS == Platform.ios) Behavior.padding
        else Behavior.height
      }
    )(
      <.ScrollView(
        ^.keyboardDismissMode := KeyboardDismissMode.`on-drag`,
        ^.keyboardShouldPersistTaps := KeyboardShouldPersistTaps.handled
      )(
        <.Text(^.rnStyle := styles.heading)(
          "Welcome to CodeGalaxy"
        ),

        <.TextInput(
          ^.placeholder := "E-MAIL-ADDRESS",
          ^.rnStyle := styles.input,
          ^.keyboardType := KeyboardType.`email-address`,
          ^.autoCapitalize := AutoCapitalize.none,
          ^.autoCompleteType := AutoCompleteType.off, // android
          ^.autoCorrect := false,
          ^.value := email,
          ^.onChangeText := setEmail
        )(),

        <.TextInput(
          ^.placeholder := "PASSWORD",
          ^.rnStyle := styles.input,
          ^.secureTextEntry := true,
          ^.value := password,
          ^.onChangeText := setPassword
        )(),
  
        <.TouchableOpacity(
          ^.disabled := disabled,
          ^.onPress := { () =>
            props.onLogin(email, password)
          }
        )(
          <.View(^.rnStyle := styles.button)(
            <.Text(^.rnStyle := js.Array(
              styles.buttonText,
              if (disabled) styles.buttonTextDisabled
              else styles.buttonTextEnabled
            ))("Login")
          )
        ),

        <.TouchableOpacity(
          ^.rnStyle := styles.signupButton,
          ^.onPress := props.onSignup
        )(
          <.Text(^.rnStyle := styles.signupText)(
            "Sign Up"
          )
        )
      )
    )
  }

  private[auth] lazy val styles = StyleSheet.create(new Styles)
  private[auth] class Styles extends js.Object {
    import Style._
    import ViewStyle._

    val container: Style = new ViewStyle {
      override val backgroundColor = CodeGalaxyTheme.Colors.primary
      override val flex = 1
    }
    val heading: Style = new ViewStyle with TextStyle {
      override val marginTop = 50
      override val color = Color.white
      override val fontSize = 40
      override val marginBottom = 10
      override val alignSelf = AlignSelf.center
    }
    val input: Style = new TextStyle {
      override val margin = 10
      override val backgroundColor = Color.white
      override val paddingHorizontal = 8
      override val height = 50
    }
    val button: Style = new ViewStyle {
      override val height = 50
      override val backgroundColor = "#666"
      override val justifyContent = JustifyContent.center
      override val alignItems = AlignItems.center
      override val margin = 10
    }
    val buttonText: Style = new TextStyle {
      override val fontSize = 18
    }
    val buttonTextEnabled: Style = new TextStyle {
      override val color = Color.white
    }
    val buttonTextDisabled: Style = new TextStyle {
      override val color = Color.black
    }
    val signupButton: Style = new ViewStyle {
      override val justifyContent = JustifyContent.center
      override val alignItems = AlignItems.center
      override val margin = 10
    }
    val signupText: Style = new TextStyle {
      override val fontSize = 16
      override val color = Color.white
    }
  }
}
