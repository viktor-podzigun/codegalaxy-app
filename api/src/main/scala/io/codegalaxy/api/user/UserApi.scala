package io.codegalaxy.api.user

import scala.concurrent.Future

trait UserApi {

  def authenticate(user: String, password: String): Future[Unit]

  def getUserProfile(force: Boolean): Future[UserProfileData]
}