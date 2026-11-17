// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    // alias del plugin de serializacion de kotlin anadido por mi
    alias(libs.plugins.kotlin.serialization) apply false
}

/*
"Conozco este plugin y lo pongo en el classpath, pero no lo
ejecutes en este proyecto; solo hazlo disponible para
include(":app")."


Sitio	Papel	¿Se ejecuta?
libs.versions.toml
define id + versión (fuente única)
no, es solo definición

build.gradle.kts raíz
declara para que los módulos puedan usarlo
con apply false → no; hoy sin él → sí, se aplica al root

app/build.gradle.kts
aplica el plugin al código de :app
aquí es donde genera los serializers

dependencies { } de :app
librería de runtime (kotlinx-serialization-json:1.7.3)
se ejecuta en el teléfono
 */