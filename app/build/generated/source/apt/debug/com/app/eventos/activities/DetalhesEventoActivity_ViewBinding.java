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

public class DetalhesEventoActivity_ViewBinding implements Unbinder {
  private DetalhesEventoActivity target;

  @UiThread
  public DetalhesEventoActivity_ViewBinding(DetalhesEventoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetalhesEventoActivity_ViewBinding(DetalhesEventoActivity target, View source) {
    this.target = target;

    target.txtVerEvento = Utils.findRequiredViewAsType(source, R.id.txt_ver_evento, "field 'txtVerEvento'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DetalhesEventoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txtVerEvento = null;
  }
}
