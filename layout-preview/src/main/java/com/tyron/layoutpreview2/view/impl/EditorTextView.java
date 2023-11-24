package com.tyron.layoutpreview2.view.impl;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import com.tyron.layoutpreview2.ViewManager;
import com.tyron.layoutpreview2.view.EditorView;

public class EditorTextView extends AppCompatTextView implements EditorView {

  private ViewManager mViewManger;

  public EditorTextView(Context context) {
    super(context);
  }

  @NonNull
  @Override
  public View getAsView() {
    return this;
  }

  @NonNull
  @Override
  public ViewManager getViewManager() {
    return mViewManger;
  }

  @Override
  public void setViewManager(@NonNull ViewManager manager) {
    mViewManger = manager;
  }
}
