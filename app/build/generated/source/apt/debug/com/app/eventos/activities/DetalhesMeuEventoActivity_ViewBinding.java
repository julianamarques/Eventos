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

public class DetalhesMeuEventoActivity_ViewBinding implements Unbinder {
  private DetalhesMeuEventoActivity target;

  @UiThread
  public DetalhesMeuEventoActivity_ViewBinding(DetalhesMeuEventoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetalhesMeuEventoActivity_ViewBinding(DetalhesMeuEventoActivity target, View source) {
    this.target = target;

    target.tvInformacoesMeuEvento = Utils.findRequiredViewAsType(source, R.id.tv_informacoes_meu_evento, "field 'tvInformacoesMeuEvento'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DetalhesMeuEventoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvInformacoesMeuEvento = null;
  }
}
