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

public class CadastroEventosActivity_ViewBinding implements Unbinder {
  private CadastroEventosActivity target;

  private View view2131230766;

  @UiThread
  public CadastroEventosActivity_ViewBinding(CadastroEventosActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CadastroEventosActivity_ViewBinding(final CadastroEventosActivity target, View source) {
    this.target = target;

    View view;
    target.editNomeEvento = Utils.findRequiredViewAsType(source, R.id.edit_nome_evento, "field 'editNomeEvento'", TextInputEditText.class);
    target.editDescricaoEvento = Utils.findRequiredViewAsType(source, R.id.edit_descricao_do_evento, "field 'editDescricaoEvento'", TextInputEditText.class);
    target.editLocalEvento = Utils.findRequiredViewAsType(source, R.id.edit_local_evento, "field 'editLocalEvento'", TextInputEditText.class);
    target.editDataInicioEvento = Utils.findRequiredViewAsType(source, R.id.edit_data_inicio_evento, "field 'editDataInicioEvento'", TextInputEditText.class);
    target.editHoraInicioEvento = Utils.findRequiredViewAsType(source, R.id.edit_hora_inicio_evento, "field 'editHoraInicioEvento'", TextInputEditText.class);
    target.editDataFimEvento = Utils.findRequiredViewAsType(source, R.id.edit_data_fim_evento, "field 'editDataFimEvento'", TextInputEditText.class);
    view = Utils.findRequiredView(source, R.id.btn_salvar_evento, "method 'salvarEvento'");
    view2131230766 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.salvarEvento();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CadastroEventosActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editNomeEvento = null;
    target.editDescricaoEvento = null;
    target.editLocalEvento = null;
    target.editDataInicioEvento = null;
    target.editHoraInicioEvento = null;
    target.editDataFimEvento = null;

    view2131230766.setOnClickListener(null);
    view2131230766 = null;
  }
}
