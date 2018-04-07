var DexClassLoader=Packages.dalvik.system.DexClassLoader;

var System=java.lang.System;
var InvocationHandler=java.lang.reflect.InvocationHandler;

var Activity=android.app.Activity;
var Context=android.content.Context;
var Intent=android.content.Intent;
var ActivityInfo=android.content.pm.ActivityInfo;
var PackageManager=android.content.pm.PackageManager;
var ResolveInfo=android.content.pm.ResolveInfo;
var Resources=android.content.res.Resources;

var ctxMc=com.mojang.minecraftpe.MainActivity.currentMainActivity.get();

var moduleApplication="com.xhh.modpe.java4modpe.module.J4MApplication";
var modpePackage="com.xhh.modpe.java4modpe";

var object;

try{
  var intent=new Intent();
  intent.setPackage(modpePackage);

  var packageManager=ctxMc.getPackageManager();
  var activitys=packageManager.queryIntentActivities(intent,0);
  var resolveInfo=activitys.get(0);
  var activityInfo=resolveInfo.activityInfo;

  var resources=packageManager.getResourcesForApplication(intent.getPackage());
  var div=System.getProperty("path.separator");
  var packageName=activityInfo.packageName;
  var dexPath=activityInfo.applicationInfo.sourceDir;
  var dexOutputDir=ctxMc.getApplicationInfo().dataDir;
  var libPath=activityInfo.applicationInfo.nativeLibraryDir;
  var ctxMod = ctxMc.createPackageContext(modpePackage,  Context.CONTEXT_IGNORE_SECURITY | Context.CONTEXT_INCLUDE_CODE) ;

  var dcLoader=new DexClassLoader(dexPath, dexOutputDir, libPath, ctxMc.getClassLoader());
  var clazz=dcLoader.loadClass(moduleApplication);
  object = clazz.newInstance();
  object.init(ctxMc, ctxMod);
}catch(e){
  print(e);
}

function attackHook(attacker, victim){
  if(object!=undefined)object.attackHook(attacker, victim);
}

function chatHook(str){
  if(object!=undefined)object.chatHook(str);
}

function continueDestroyBlock(x, y, z, side, progress){
  if(object!=undefined)object.continueDestroyBlock(x, y, z, side, progress);
}

function destroyBlock(x, y, z, side){
  if(object!=undefined)object.destroyBlock(x, y, z, side);
}

function projectileHitEntityHook(projectile, targetEntity){
  if(object!=undefined)object.projectileHitEntityHook(projectile, targetEntity);
}

function eatHook(hearts, saturationRatio){
  if(object!=undefined)object.eatHook(hearts, saturationRatio);
}

function entityAddedHook(entity){
  if(object!=undefined)object.entityAddedHook(entity);
}

function entityHurtHook(attacker, victim, halfhearts){
  if(object!=undefined)object.entityHurtHook(attacker, victim, halfhearts);
}

function entityRemovedHook(entity){
  if(object!=undefined)object.entityRemovedHook(entity);
}

function explodeHook(entity, x, y, z, power, onFire){
  if(object!=undefined)object.explodeHook(entity, x, y, z, power, onFire);
}

function serverMessageReceiveHook(str){
  if(object!=undefined)object.serverMessageReceiveHook(str);
}

function chatReceiveHook(str, sender){
  if(object!=undefined)object.chatReceiveHook(str, sender);
}

function leaveGame(){
  if(object!=undefined)object.leaveGame();
}

function deathHook(attacker, victim){
  if(object!=undefined)object.deathHook(attacker, victim);
}

function playerAddExpHook(player, experienceAdded){
  if(object!=undefined)object.playerAddExpHook(player, experienceAdded);
}

function playerExpLevelChangeHook(player, levelsAdded){
  if(object!=undefined)object.playerExpLevelChangeHook(player, levelsAdded);
}

function redstoneUpdateHook(x, y, z, newCurrent, worldLoading, blockId, blockDamage){
  if(object!=undefined)object.redstoneUpdateHook(x, y, z, newCurrent, worldLoading, blockId, blockDamage);
}

function screenChangeHook(screenName){
  if(object!=undefined)object.screenChangeHook(screenName);
}

function selectLevelHook(){
  if(object!=undefined)object.selectLevelHook();
}

function newLevel(){
  if(object!=undefined)object.newLevel();
}

function startDestroyBlock(x, y, z, side){
  if(object!=undefined)object.startDestroyBlock(x, y, z, side);
}

function projectileHitBlockHook(projectile, blockX, blockY, blockZ, side){
  if(object!=undefined)object.projectileHitBlockHook(projectile, blockX, blockY, blockZ, side);
}

function modTick(){
  if(object!=undefined)object.modTick();
}

function useItem(x, y, z, itemid, blockid, side, itemDamage, blockDamage){
  if(object!=undefined)object.useItem(x, y, z, itemid, blockid, side, itemDamage, blockDamage);
}