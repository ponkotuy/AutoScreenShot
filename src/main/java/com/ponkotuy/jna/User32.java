package com.ponkotuy.jna;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;

public interface User32 extends StdCallLibrary
{
    User32 instance = (User32) Native.loadLibrary("user32", User32.class);
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

    int GW_HWNDNEXT = 2;

    void keybd_event(byte bVk, byte bScan, int dwFlags, int dwExtraInfo);
}
