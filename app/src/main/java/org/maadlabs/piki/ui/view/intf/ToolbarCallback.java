package org.maadlabs.piki.ui.view.intf;

import android.os.Bundle;

public interface ToolbarCallback {
        Bundle onSaveToolbarData();
        void onRestoreToolbarData(Bundle bundle);
    }