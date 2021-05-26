package com.rsschool.android2021

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.fragment.app.Fragment


class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var min_value: EditText? = null
    private var max_value: EditText? = null
    private lateinit var mainActivity: MainActivity


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        min_value = view.findViewById(R.id.min_value)
        max_value = view.findViewById(R.id.max_value)
        mainActivity = getActivity() as MainActivity
        //generateButton.post(generateButton.visibility = View.GONE )

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        generateButton?.setOnClickListener {
            val min = Utils.getInt(min_value)
            val max = Utils.getInt(max_value)


            if (isValuesGood(min, max)) {
                mainActivity.showSecondFragment(min, max)
            } else {
                Toast.makeText(mainActivity, getMessageBadValues(min, max), Toast.LENGTH_SHORT)
                    .show()

            }
        }
    }

    fun isValuesGood(min: Int, max: Int): Boolean {
        return (min < max) && (min_value?.text.toString().length>0);
    }

    fun getMessageBadValues(min: Int, max: Int): String {
        var valueNames: String=""

        if (min_value?.text.toString().length == 0) {
            valueNames = " MIN"
        }
        if (max_value?.text.toString().length == 0) {
            if (valueNames.length > 0) {
                valueNames = "s$valueNames and "
            }
            valueNames = "$valueNames MAX"
        }
        if (valueNames.length > 0) {
            return "Input value$valueNames, please."
        } else if (min >= max) {
            return "Value MAX must be more then MIN."
        }
        return ""
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}


