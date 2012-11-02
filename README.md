<html>
<body>

<h1>IDResolver 1.1</h1>

Only works on exisiting (forge) configs

It will (try) to cleverly arrange your IDs so that they're all grouped.

It's 100% compaitible with forge (I copied their configuration loading/saving code)

It will put all your BlockIDs below 4096 and your ItemIDs above.

Makes sure that item IDs are above 4096, can be changed by using -Didres.needsBigItemID=false

Has support for NEI and InvTweaks (which use non-forge config formats)<br />
(by 'support' i mean 'ignores')

Also ensures that extrabiomes is allocated IDs first 
if it's installed (otherwise MC crashes)

<b>Don't run on existing configs (if you do, BACK THEM UP) - I HAVE NO RESPONSIBILITY FOR WHAT HAPPENS!!!</b>

<h1>License</h1>
See MinecraftForge-License.txt
</body>
</html>
