// Generated code from Butter Knife. Do not modify!
package com.app.eventos.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.eventos.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class InscricaoActivity_ViewBinding implements Unbinder {
  private InscricaoActivity target;

  @UiThread
  public InscricaoActivity_ViewBinding(InscricaoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public InscricaoActivity_ViewBinding(InscricaoActivity target, View source) {
    this.target = target;

    target.tvInformacoesEventos = Utils.findRequiredViewAsType(source, R.id.tv_informacoes_evento, "field 'tvInformacoesEventos'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    InscricaoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvInformacoesEventos = null;
  }
}
