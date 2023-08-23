package com.mayoclinicdemo

import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ViewManager
import java.util.Collections

/**
 * A React Native package that provides access to the APIModule native module.
 * 
 * This package is responsible for creating native modules and view managers that can be
 * used in a React Native application.
 */
class ApiPackage : ReactPackage {
    /**
     * Creates native modules for use in a React Native application.
     *
     * @param reactContext The ReactApplicationContext provided by React Native.
     * @return A list of native modules to be used in the application.
     */
    override fun createNativeModules(reactContext: ReactApplicationContext): List<NativeModule> {
        return arrayListOf(ApiModule(reactContext))
    }

    /**
     * Creates view managers for use in a React Native application.
     *
     * @param reactContext The ReactApplicationContext provided by React Native.
     * @return A list of view managers to be used in the application.
     */
    override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<*, *>> {
        // Return an empty list as this package does not create view managers.
        return Collections.emptyList()
    }
}