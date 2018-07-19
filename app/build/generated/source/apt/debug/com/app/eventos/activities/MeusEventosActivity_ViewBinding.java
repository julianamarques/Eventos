// Generated code from Butter Knife. Do not modify!
package com.app.eventos.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.app.eventos.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MeusEventosActivity_ViewBinding implements Unbinder {
  private MeusEventosActivity target;

  private View view2131230817;

  @UiThread
  public MeusEventosActivity_ViewBinding(MeusEventosActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MeusEventosActivity_ViewBinding(final MeusEventosActivity target, View source) {
    this.target = target;

    View view;
    target.recyclerMeusEventos = Utils.findRequiredViewAsType(source, R.id.rv_lista_meus_eventos, "field 'recyclerMeusEventos'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.fab_adicionar_eventos, "method 'abrirCadastroEventos'");
    view2131230817 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.abrirCadastroEventos();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MeusEventosActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerMeusEventos = null;

    view2131230817.setOnClickListener(null);
    view2131230817 = null;
  }
}
