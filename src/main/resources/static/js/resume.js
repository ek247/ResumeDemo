/**
 * Created by worri on 6/9/2016.
 */
$(document).ready(function() {
    var numItems = '${size}';
    prompt("Size = " + numItems);
    for(i = 1; i < size; i++)
    {
        prompt("At: ", i);
        $('#hide'+i).hide();
    }


});