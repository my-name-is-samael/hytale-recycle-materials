# RecycleMaterials

Hytale server mod: recycling recipes for building blocks, configurable in game by admins (`/recyclematerials`).

## Build

`build.bat` compiles against `..\HytaleServer.jar` with the JDK 25 and writes `build\RecycleMaterials.jar`.
`build.bat deploy` also copies it into `..\Server\mods` (stop the server first: Windows locks the loaded jar).

## Layout

- `recipes/RecipeCatalog` : every recipe, generated from material lists.
- `recipes/RecipeSet` : collects recipes and skips the ones referencing unknown items.
- `recipes/RecipeManager` : registers the recipes and swaps them live when the config changes.
- `config/` : one toggle per `RecycleCategory`, plus `RecyclePreset`s.
- `ui/SettingsUI` + `Common/UI/Custom/RecycleMaterials/*.ui` : the settings window.
