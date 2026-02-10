/// <reference types="vite/client" />

interface ImportMetaEnv {
  readonly BACKEND_API: string
}

interface ImportMeta {
  readonly env: ImportMetaEnv
}
