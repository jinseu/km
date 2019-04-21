# Solution One
cat file.txt | sed '10!d'

# Solution Two
awk 'NR==10{print $0}' file.txt

# Solution Three
sed -n '10p' file.txt