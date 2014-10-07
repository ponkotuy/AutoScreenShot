package com.ponkotuy.jna;

import com.sun.jna.win32.StdCallLibrary;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2014/10/04.
 */
public class WindowList {
    public static List<Window> get() {
        final List<Window> inflList = new ArrayList<Window>();
        final List<Integer> order = new ArrayList<Integer>();
        int top = User32.instance.GetTopWindow(0);
        while (top != 0) {
            order.add(top);
            top = User32.instance.GetWindow(top, User32.GW_HWNDNEXT);
        }
        User32.instance.EnumWindows(new WndEnumProc() {
            @Override
            public boolean callback(int hWnd, int lParam) throws Exception {
                if (User32.instance.IsWindowVisible(hWnd)) {
                    inflList.add(new Window(User32.instance, hWnd));
                }
                return true;
            }
        }, 0);
        return inflList;
    }

    public static interface WndEnumProc extends StdCallLibrary.StdCallCallback {
        boolean callback (int hWnd, int lParam) throws Exception;
    }

}
