#!/usr/bin/ruby -w

# https://www.facebook.com/hackercup/problems.php?pid=403525256396727&round=185564241586420

def balanced?(s)
  min_open = max_open = 0

  s.enum_for(:each_char).each_with_index do |char, i|
    case char
    when '('
      max_open += 1
      min_open += 1 if s[i - 1] != ':'
    when ')'
      min_open = [0, min_open - 1].max
      max_open -= 1 if s[i - 1] != ':'
    break if max_open < 0
    end
  end

  max_open >= 0 && min_open == 0
end

t = gets.chomp.to_i

t.times do |i|
  s = gets.chomp
  puts "Case ##{i + 1}: %s" % [balanced?(s) ? "YES" : "NO"]
end
