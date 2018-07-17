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

public class CadastroUsuarioActivity_ViewBinding implements Unbinder {
  private CadastroUsuarioActivity target;

  private View view2131230767;

  @UiThread
  public CadastroUsuarioActivity_ViewBinding(CadastroUsuarioActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CadastroUsuarioActivity_ViewBinding(final CadastroUsuarioActivity target, View source) {
    this.target = target;

    View view;
    target.editNome = Utils.findRequiredViewAsType(source, R.id.edit_nome, "field 'editNome'", TextInputEditText.class);
    target.editEmail = Utils.findRequiredViewAsType(source, R.id.edit_email, "field 'editEmail'", TextInputEditText.class);
    target.editSenha = Utils.findRequiredViewAsType(source, R.id.edit_senha, "field 'editSenha'", TextInputEditText.class);
    target.editRedigiteSenha = Utils.findRequiredViewAsType(source, R.id.edit_redigite_senha, "field 'editRedigiteSenha'", TextInputEditText.class);
    view = Utils.findRequiredView(source, R.id.btn_salvar_usuario, "method 'salvarUsuario'");
    view2131230767 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.salvarUsuario(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CadastroUsuarioActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editNome = null;
    target.editEmail = null;
    target.editSenha = null;
    target.editRedigiteSenha = null;

    view2131230767.setOnClickListener(null);
    view2131230767 = null;
  }
}
