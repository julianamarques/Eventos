// Generated code from Butter Knife. Do not modify!
package com.app.eventos.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.eventos.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EventosAdapter$ViewHolder_ViewBinding implements Unbinder {
  private EventosAdapter.ViewHolder target;

  @UiThread
  public EventosAdapter$ViewHolder_ViewBinding(EventosAdapter.ViewHolder target, View source) {
    this.target = target;

    target.txtNomeEvento = Utils.findRequiredViewAsType(source, R.id.txt_nome_evento, "field 'txtNomeEvento'", TextView.class);
    target.txtDescricaoEvento = Utils.findRequiredViewAsType(source, R.id.txt_descricao_evento, "field 'txtDescricaoEvento'", TextView.class);
    target.txtLocalEvento = Utils.findRequiredViewAsType(source, R.id.txt_local_evento, "field 'txtLocalEvento'", TextView.class);
    target.txtDataInicioEvento = Utils.findRequiredViewAsType(source, R.id.txt_data_inicio_evento, "field 'txtDataInicioEvento'", TextView.class);
    target.txtHoraInicioEvento = Utils.findRequiredViewAsType(source, R.id.txt_hora_inicio_evento, "field 'txtHoraInicioEvento'", TextView.class);
    target.txtDataFimEvento = Utils.findRequiredViewAsType(source, R.id.txt_data_fim_evento, "field 'txtDataFimEvento'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EventosAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txtNomeEvento = null;
    target.txtDescricaoEvento = null;
    target.txtLocalEvento = null;
    target.txtDataInicioEvento = null;
    target.txtHoraInicioEvento = null;
    target.txtDataFimEvento = null;
  }
}
