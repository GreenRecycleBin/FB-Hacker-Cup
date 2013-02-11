#!/usr/bin/ruby -w

# https://www.facebook.com/hackercup/problems.php?pid=475986555798659&round=185564241586420

def score(s)
  s.downcase!

  chars = s.enum_for(:each_char).select { |c| c =~ /[[:alpha:]]/ }
  hash = chars.each_with_object(Hash.new(0)) { |c, h| h[c] += 1 }

  counts = hash.values.sort { |a, b| b <=> a }
  counts_and_values = counts.zip(26.downto(0))
  counts_and_values.reduce(0) { |s, o| s + o[0] * o[1] }
end

m = gets.chomp.to_i

m.times do |i|
  s = gets.chomp

  puts "Case ##{i + 1}: #{score s} "
end
