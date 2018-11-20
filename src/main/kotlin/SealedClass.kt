sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

fun eval(expr: Expr): Double = when(expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN
    // the `else` clause is not required because we've covered all the cases
}

// Nesting sealed classes can get messy
sealed class LoginState {
    sealed class SignedOut : LoginState() {
        object HaveAccount : SignedOut()
        object NewUser : SignedOut()
    }

    object SignedIn : LoginState()
}

fun ohNo(state: LoginState) {
    val test= when (state) {
        is LoginState.SignedOut -> TODO()
        LoginState.SignedIn -> TODO()
    }
}