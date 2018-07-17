// Generated code from Butter Knife. Do not modify!
package com.app.eventos.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.app.eventos.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view2131230763;

  private View view2131230760;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.editLoginEmail = Utils.findRequiredViewAsType(source, R.id.edit_login_email, "field 'editLoginEmail'", TextInputEditText.class);
    target.editLoginSenha = Utils.findRequiredViewAsType(source, R.id.edit_login_senha, "field 'editLoginSenha'", TextInputEditText.class);
    view = Utils.findRequiredView(source, R.id.btn_entrar, "method 'entrar'");
    view2131230763 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.entrar(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_abrir_cadastro_usuario, "method 'abrirCadastroUsuario'");
    view2131230760 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.abrirCadastroUsuario();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editLoginEmail = null;
    target.editLoginSenha = null;

    view2131230763.setOnClickListener(null);
    view2131230763 = null;
    view2131230760.setOnClickListener(null);
    view2131230760 = null;
  }
}
