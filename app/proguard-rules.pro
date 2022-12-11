-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

-keepattributes RuntimeVisibleAnnotations,AnnotationDefault
-keep class app.suhasdissa.lyrics.backend.repositories.data.SongUpdate { 
  <init>(...);
  *; 
}
