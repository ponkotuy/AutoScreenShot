package com.ponkotuy.jna;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;

/**
* Created by yosuke on 2014/10/04.
*/
public interface User32 extends StdCallLibrary
{
    final User32 instance = (User32) Native.loadLibrary("user32", User32.class);
    boolean EnumWindows (WindowList.WndEnumProc wndenumproc, int lParam);
    boolean IsWindowVisible(int hWnd);
    boolean GetClientRect(int hWnd, Rect r);
    boolean ClientToScreen(int hWnd, Rect r);
    int GetWindowRect(int hWnd, Rect r);
    void GetWindowTextA(int hWnd, byte[] buffer, int buflen);
    int GetTopWindow(int hWnd);
    int GetWindow(int hWnd, int flag);
    int SetFocus(int hWnd);
    int SetActiveWindow(int hWnd);

    final int GW_HWNDNEXT = 2;
}
