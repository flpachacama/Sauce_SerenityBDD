Feature: Login en SauceDemo
  Como usuario del portal
  Quiero autenticarme con diferentes credenciales
  Para validar acceso correcto y mensajes de error

  @workflow_v2 @login
  Scenario Outline: Validacion de login
    Given el usuario abre la pagina de SauceDemo
    When inicia sesion con "<username>" y "<password>"
    Then valida "<resultado_login>"

    Examples:
      | username      | password     | resultado_login |
      | standard_user | secret_sauce | success         |
      | locked_user   | secret_sauce | error_login     |
      | standard_user | wrong_pass   | error_login     |
