Feature: Flujo de compra en SauceDemo
  Como equipo QA
  Quiero ejecutar escenarios data-driven
  Para cubrir flujos positivos y negativos del E2E

  @workflow_v2 @outline
  Scenario Outline: Flujo E2E con diferentes tipos de usuario
    Given el usuario abre la pagina de SauceDemo
    When inicia sesion con "<username>" y "<password>"
    Then valida "<resultado_login>"

    When agrega "<producto>" al carrito
    Then valida "<resultado_producto>"

    When visualiza el carrito
    Then valida "<resultado_carrito>"

    When completa checkout usando "<checkout_case>"
    Then valida "<resultado_checkout>"

    Examples:
      | username      | password     | producto            | checkout_case   | resultado_login | resultado_producto  | resultado_carrito | resultado_checkout |
      | standard_user | secret_sauce | Sauce Labs Backpack | checkout_happy  | success         | agregado            | carrito_con_items | compra_exitosa     |
      | locked_user   | secret_sauce | Sauce Labs Backpack | no_aplica       | error_login     | no_aplica           | no_aplica         | no_aplica          |
      | standard_user | wrong_pass   | Sauce Labs Backpack | no_aplica       | error_login     | no_aplica           | no_aplica         | no_aplica          |
      | standard_user | secret_sauce | producto_fake       | checkout_happy  | success         | producto_no_existe  | carrito_vacio     | checkout_bloqueado |
